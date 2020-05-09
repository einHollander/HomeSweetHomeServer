package bedbrains.homesweethomeserver.rest

import bedbrains.homesweethomeserver.DataRepository
import bedbrains.shared.datatypes.devices.Device
import bedbrains.shared.datatypes.rules.Rule
import bedbrains.shared.datatypes.rules.RuleValue
import bedbrains.shared.datatypes.update
import bedbrains.shared.datatypes.upsert
import org.springframework.web.bind.annotation.*

@RestController
class Controller {
    @GetMapping("/v1/devices")
    fun devices() = DataRepository.devices

    @GetMapping("/v1/devices/{uid}")
    fun device(@PathVariable(name = "uid") uid: String) = DataRepository.devices.find { it.uid == uid }

    @PutMapping("/v1/devices/device")
    fun putDevice(@RequestBody device: Device) = DataRepository.devices.update(device) { it.uid == device.uid }

    @GetMapping("/v1/rules")
    fun rules() = DataRepository.rules

    @GetMapping("/v1/rules/{uid}")
    fun rule(@PathVariable(name = "uid") uid: String) = DataRepository.rules.find { it.uid == uid }

    @PostMapping("/v1/rules/rule")
    fun postRule(@RequestBody rule: Rule) = DataRepository.rules.upsert(rule) { it.uid == rule.uid }

    @DeleteMapping("/v1/rules/{uid}")
    fun deleteRule(@PathVariable(name = "uid") uid: String) = DataRepository.rules.removeIf { it.uid == uid }

    @GetMapping("/v1/values")
    fun values() = DataRepository.values

    @GetMapping("/v1/values/{uid}")
    fun value(@PathVariable(name = "uid") uid: String) = DataRepository.values.find { it.uid == uid }

    @PostMapping("/v1/values/value")
    fun postValue(@RequestBody value: RuleValue) = DataRepository.values.upsert(value) { it.uid == value.uid }

    @DeleteMapping("/v1/values/{uid}")
    fun deleteValue(@PathVariable(name = "uid") uid: String) = DataRepository.values.removeIf { it.uid == uid }
}
