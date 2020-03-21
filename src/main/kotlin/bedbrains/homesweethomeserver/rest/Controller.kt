package bedbrains.homesweethomeserver.rest

import bedbrains.shared.datatypes.devices.Device
import bedbrains.shared.datatypes.devices.Heating
import bedbrains.shared.datatypes.devices.Light
import bedbrains.shared.datatypes.rules.*
import bedbrains.shared.datatypes.temperature.celsius
import bedbrains.shared.datatypes.upsert
import org.springframework.web.bind.annotation.*

@RestController
class Controller {

    val devices = mutableListOf<Device>(
            Light("9998", "Keller", "Decke", true),
            Heating("9999", "Keller", "Heizung", 22.celsius, 20.celsius)
    )
    val rules = mutableListOf<Rule>(
            WeeklyRule("10000", "Normal").apply { timeSpans.add(WeeklyTimeSpan(WeeklyTime.now, WeeklyTime.MAX)) },
            WeeklyRule("10001", "Ferien")
    )
    val values = mutableListOf<RuleValue>(
            RuleValue("10002", "Default", 22.celsius, true),
            RuleValue("10003", "Winter", 23.celsius, true)
    )

    @GetMapping("/devices")
    fun devices() = devices

    @GetMapping("/device")
    fun device(@RequestParam(name = "uid") uid: String) = devices.find { it.uid == uid }

    @PostMapping("/device")
    fun postDevice(@RequestBody device: Device) {
        devices.upsert(device) { it.uid == device.uid }
    }

    @DeleteMapping("/device")
    fun deleteDevice(@RequestParam(name = "uid") uid: String) {
        devices.removeIf { it.uid == uid }
    }

    @GetMapping("/rules")
    fun rules() = rules

    @GetMapping("/rule")
    fun rule(@RequestParam(name = "uid") uid: String) = rules.find { it.uid == uid }

    @PostMapping("/rule")
    fun postrule(@RequestBody rule: Rule) {
        rules.upsert(rule) { it.uid == rule.uid }
    }

    @DeleteMapping("/rule")
    fun deleteRule(@RequestParam(name = "uid") uid: String) {
        rules.removeIf { it.uid == uid }
    }

    @GetMapping("/values")
    fun values() = values

    @GetMapping("/value")
    fun value(@RequestParam(name = "uid") uid: String) = values.find { it.uid == uid }

    @PostMapping("/value")
    fun postValue(@RequestBody value: RuleValue) {
        values.upsert(value) { it.uid == value.uid }
    }

    @DeleteMapping("/value")
    fun deleteValue(@RequestParam(name = "uid") uid: String) {
        values.removeIf { it.uid == uid }
    }

}
