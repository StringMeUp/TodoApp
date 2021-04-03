package com.string.me.up.todohiltassignment.di

interface ApiFactory {
    fun <T> buildApi(type: Class<T>): T
}
