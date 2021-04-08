package com.zz.lib_compiler;

import com.google.auto.common.MoreElements;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import com.zz.annotations.MyBindView;


@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        //获取所有的带有BindView注解的元素
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(MyBindView.class);

        // 按照Activity 对 BindView 注解的元素进行分类
        Map<String, List<VariableElement>> activityElementMap = new HashMap<>();
        for (Element element : elements) {

            //获取对应的MainActivity名称
            VariableElement variableElement = (VariableElement) element;
            TypeElement enclosingElement = (TypeElement) variableElement.getEnclosingElement();

            String packageName = MoreElements.getPackage(enclosingElement).getQualifiedName().toString();
            String activityName = enclosingElement.getQualifiedName().toString();

            //按照activity的包路径进行分类
            List<VariableElement> elementList = activityElementMap.get(activityName);
            if (elementList == null) {
                elementList = new ArrayList<>();
                activityElementMap.put(activityName, elementList);
            }
            elementList.add(variableElement);
        }

        // 生成java 文件
        Set<Map.Entry<String, List<VariableElement>>> entries = activityElementMap.entrySet();
        for (Map.Entry<String, List<VariableElement>> entry : entries) {
            String activityName = entry.getKey();
            String substring = activityName.substring(activityName.lastIndexOf(".") + 1, activityName.length());

            //创建类
            TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(substring + "_ViewBinding")
                    .addModifiers(Modifier.PUBLIC);
            //创建构造方法
            MethodSpec.Builder methodBuilder = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(TypeVariableName.get(activityName), "target");

            List<VariableElement> elementList = entry.getValue();
            String packageName = MoreElements.getPackage(elementList.get(0)).getQualifiedName().toString();
            for (VariableElement element : elementList) {
                String name = element.getSimpleName().toString();
                TypeMirror typeMirror = element.asType();
                int id = element.getAnnotation(MyBindView.class).value();
                methodBuilder.addStatement("target." + name + " = (" + typeMirror + ")" + "target.findViewById(" + id + ")");
            }
            MethodSpec methodSpec = methodBuilder.build();
            TypeSpec typeSpec = typeBuilder.addMethod(methodSpec).build();

            JavaFile javaFile = JavaFile.builder(packageName, typeSpec)
                    .build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        String canonicalName = MyBindView.class.getCanonicalName();
        annotations.add(canonicalName);
        return annotations;
    }
}