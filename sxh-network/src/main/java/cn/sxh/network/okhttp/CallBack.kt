package cn.sxh.network.okhttp

import java.io.IOException

interface CallBack {

    fun onFail(call: Call, e: IOException)

    fun onResponse(call: Call, response: Response): IOException
}