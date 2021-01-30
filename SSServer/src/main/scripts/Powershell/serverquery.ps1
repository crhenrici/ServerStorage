$ramOutput = Get-WmiObject win32_physicalmemory |
        Select-Object @{n="Capacity";e={$_.Capacity/1GB}}
$ramUsage = (Get-WmiObject Win32_ComputerSystem).totalphysicalmemory / (1024 * 1024 * 1024)
$cpuUsage = Get-Counter '\Processor(_total)\% Processor Time'|
        Select-Object -expand CounterSamples
$sysyemName = Get-WmiObject win32_volume | Select-Object SystemName -ExpandProperty sysyemName -First 1
$output = [ordered]@{
    CpuUsage = $cpuUsage
    Ram = $ramOutput
    RamUsage = $ramUsage
    SystemName = $sysyemName
}
$jsonOut  = $output | ConvertTo-Json

Invoke-RestMethod -Method Post -Uri "http://localhost:9010/service/save/server" -Body ($jsonOut) -ContentType 'application/json'