package com.huatu.htlx.utils.mmkv

import android.componentization.base.BaseApplication
import android.content.Context
import android.os.Parcelable
import android.text.TextUtils
import com.getkeepsafe.relinker.ReLinker
import com.google.gson.Gson
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKV.MULTI_PROCESS_MODE
import java.util.*


/**
 * Created by zz on 3/26/21.
 */
class KVUtils {
    private var mContext: Context? = null

    companion object {
        private var instance: KVUtils? = null
        fun get(): KVUtils? {
            if (instance == null) {
                synchronized(KVUtils::class.java) {
                    if (instance == null) {
                        instance = KVUtils()
                    }
                }
            }
            return instance
        }

        fun putInt(key: String, value: Int) {
            get()?.getMMKV(null)?.encode(key, value)
        }

        fun putInt(name: String?, key: String, value: String) {
            get()?.getMMKV(name)?.encode(key, value)
        }

        fun putString(key: String, value: String) {
            get()?.getMMKV(null)?.encode(key, value)
        }

        fun putString(name: String?, key: String, value: String) {
            get()?.getMMKV(name)?.encode(key, value)
        }

        fun putBoolean(key: String, value: Boolean) {
            get()?.getMMKV(null)?.encode(key, value)
        }

        fun putBoolean(name: String?, key: String, value: Boolean) {
            get()?.getMMKV(name)!!.encode(key, value)
        }

        fun putLong(key: String, value: Long) {
            get()?.getMMKV(null)!!.encode(key, value)
        }

        fun putLong(name: String?, key: String, value: Long) {
            get()?.getMMKV(name)!!.encode(key, value)
        }

        fun putFloat(key: String, value: Float) {
            get()?.getMMKV(null)!!.encode(key, value)
        }

        fun putFloat(name: String?, key: String, value: Float) {
            get()?.getMMKV(name)!!.encode(key, value)
        }

        fun putDouble(key: String, value: Double) {
            get()?.getMMKV(null)!!.encode(key, value)
        }

        fun putDouble(name: String?, key: String, value: Double) {
            get()?.getMMKV(name)!!.encode(key, value)
        }

        fun putByteArray(key: String, value: ByteArray) {
            get()?.getMMKV(null)!!.encode(key, value)
        }

        fun putByteArray(name: String?, key: String, value: ByteArray) {
            get()?.getMMKV(name)!!.encode(key, value)
        }

        fun putStringSet(key: String, value: Set<String>) {
            get()?.getMMKV(null)!!.encode(key, value)
        }

        fun putStringSet(name: String?, key: String, value: Set<String>) {
            get()?.getMMKV(name)!!.encode(key, value)
        }

        fun putParcelable(key: String, value: Any) {
            if (value is Parcelable) {
                get()?.getMMKV(null)!!.encode(key, value)
            } else {
                get()?.getMMKV(null)!!.encode(key, Gson().toJson(value))
            }
        }

        fun putParcelable(name: String?, key: String, value: Any) {
            if (value is Parcelable) {
                get()?.getMMKV(name)!!.encode(key, value)
            } else {
                get()?.getMMKV(name)!!.encode(key, Gson().toJson(value))
            }
        }

        fun getInt(key: String): Int {
            return get()?.getMMKV(null)!!.decodeInt(key)!!
        }

        fun getInt(key: String, defaultValue: Int): Int {
            return get()?.getMMKV(null)!!.decodeInt(key, defaultValue)!!
        }

        fun getInt(name: String?, key: String, defaultValue: Int): Int {
            return get()?.getMMKV(name)!!.decodeInt(key, defaultValue)!!
        }

        fun getString(key: String): String {
            return get()?.getMMKV(null)!!.decodeString(key, "")!!
        }

        fun getString(key: String, defaultValue: String): String {
            return get()?.getMMKV(null)!!.decodeString(key, defaultValue)!!
        }

        fun getString(name: String?, key: String, defaultValue: String): String {
            return get()?.getMMKV(name)!!.decodeString(key, defaultValue)!!
        }

        fun getBoolean(key: String): Boolean {
            return get()?.getMMKV(null)!!.decodeBool(key, false)
        }

        fun getBoolean(key: String, defaultValue: Boolean): Boolean {
            return get()?.getMMKV(null)!!.decodeBool(key, defaultValue)
        }

        fun getBoolean(name: String?, key: String, defaultValue: Boolean) {
            get()?.getMMKV(name)!!.decodeBool(key, defaultValue)
        }

        fun getLong(key: String): Long {
            return get()?.getMMKV(null)!!.decodeLong(key, 0L)
        }

        fun getLong(key: String, defaultValue: Long): Long {
            return get()?.getMMKV(null)!!.decodeLong(key, defaultValue)
        }

        fun getLong(name: String?, key: String, defaultValue: Long): Long {
            return get()?.getMMKV(name)!!.decodeLong(key, defaultValue)
        }

        fun getFloat(key: String): Float {
            return get()?.getMMKV(null)!!.decodeFloat(key, 0f)
        }

        fun getFloat(key: String, defaultValue: Float): Float {
            return get()?.getMMKV(null)!!.decodeFloat(key, defaultValue)
        }

        fun getFloat(name: String?, key: String, defaultValue: Float): Float {
            return get()?.getMMKV(name)!!.decodeFloat(key, defaultValue)
        }

        fun getDouble(key: String): Double {
            return get()?.getMMKV(null)!!.decodeDouble(key)
        }

        fun getDouble(key: String, defaultValue: Double): Double {
            return get()?.getMMKV(null)!!.decodeDouble(key, defaultValue)
        }

        fun getDouble(name: String?, key: String, defaultValue: Double): Double {
            return get()?.getMMKV(name)!!.decodeDouble(key, defaultValue)
        }

        fun getByteArray(key: String): ByteArray? {
            return get()?.getMMKV(null)!!.decodeBytes(key)
        }

        fun getByteArray(key: String, defaultValue: ByteArray): ByteArray? {
            return get()?.getMMKV(null)!!.decodeBytes(key, defaultValue)
        }

        fun getByteArray(name: String?, key: String, defaultValue: ByteArray): ByteArray? {
            return get()?.getMMKV(name)!!.decodeBytes(key, defaultValue)
        }

        fun getStringSet(key: String): Set<String> {
            return get()?.getMMKV(null)!!.decodeStringSet(key, HashSet()) as Set<String>
        }

        fun getStringSet(key: String, defaultValue: Set<String>): Set<String> {
            return get()?.getMMKV(null)!!.decodeStringSet(key, defaultValue) as Set<String>
        }

        fun getStringSet(name: String?, key: String, defaultValue: Set<String>): Set<String> {
            return get()?.getMMKV(name)!!.decodeStringSet(key, defaultValue) as Set<String>
        }

        fun <T: Parcelable> getParcelable(key: String, bean: Class<T>): T? {
            return get()?.getMMKV(null)?.decodeParcelable(key, bean)
        }


        fun <T : Parcelable?> getParcelable(name: String, key: String, bean: Class<T>): T? {
            return get()?.getMMKV(name)!!.decodeParcelable(key, bean)
        }

        fun remove(key: String) {
            get()?.getMMKV(null)!!.remove(key)
        }

        fun remove(name: String, key: String) {
            get()?.getMMKV(name)!!.remove(key)
        }

        fun clear() {
            get()?.getMMKV(null)!!.clear()
        }

        fun clear(name: String) {
            get()?.getMMKV(name)!!.clear()
        }

        fun contains(key: String): Boolean {
            return get()?.getMMKV(null)!!.containsKey(key)
        }

        fun contains(key: String, name: String): Boolean {
            return get()?.getMMKV(name)!!.containsKey(key)
        }
    }

    fun init(context: Context) {
        this.mContext = context
        val root = context.filesDir.absolutePath + "/mmkv"
        if (android.os.Build.VERSION.SDK_INT == 19) {
            MMKV.initialize(root, object : MMKV.LibLoader {
                override fun loadLibrary(p0: String?) {
                    ReLinker.loadLibrary(mContext, p0)
                }
            })
        } else {
            MMKV.initialize(mContext)
        }
    }

    fun getMMKV(name: String?): MMKV{
        var mmkv: MMKV
        if (TextUtils.isEmpty(name)) {
            mmkv = MMKV.mmkvWithID(BaseApplication.getInstance().packageName, MULTI_PROCESS_MODE)!!
        } else {
            mmkv = MMKV.mmkvWithID(name, MULTI_PROCESS_MODE)!!
        }
        return mmkv
    }

}