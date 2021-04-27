package onlinestore

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackages = ["onlinestore"])
class OnlineStore

fun main(args: Array<String>) {
    runApplication<OnlineStore>(*args)
}

