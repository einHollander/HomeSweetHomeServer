package bedbrains.homesweethomeserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HomeSweetHomeServerApplication

fun main(args: Array<String>) {
    runApplication<HomeSweetHomeServerApplication>(*args)
}
