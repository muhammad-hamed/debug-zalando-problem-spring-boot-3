package com.example.demo

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@AutoConfiguration
class DemoApplication


fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
