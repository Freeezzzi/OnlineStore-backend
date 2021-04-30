package com.onlinestore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(scanBasePackages = ["com.onlinestore"])
@EnableScheduling
class Server

fun main(args: Array<String>) {
    runApplication<Server>(*args)
}

