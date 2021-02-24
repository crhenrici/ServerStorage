param (
    [string]$computerName = "chwisrv03"
)
$ramOutput = Get-WmiObject win32_physicalmemory -Computername $computerName |
        Select-Object @{n="Capacity";e={$_.Capacity/1GB}}
$ramUsage = (Get-WmiObject Win32_ComputerSystem -Computername $computerName).totalphysicalmemory / (1024 * 1024 * 1024)
#$cpuUsage = Get-Counter '\Processor(_total)\% Processor Time' -Computername $computerName |
#        Select-Object -expand CounterSamples
$cpuUsage =  get-counter "\\$computerName\Processor(_total)\% Processor Time" | Select-Object -expand CounterSamples
$systemName = Get-WmiObject win32_volume -Computername $computerName | Select-Object SystemName -First 1
$output = [ordered]@{
    CpuUsage = $cpuUsage
    Ram = $ramOutput
    RamUsage = $ramUsage
    SystemName = $systemName
}
$jsonOut  = $output | ConvertTo-Json

Invoke-RestMethod -Method Post -Uri "http://localhost:9010/service/save/server" -Body ($jsonOut) -ContentType 'application/json'
$jsonOut | Out-File -FilePath .\log\serverQuery.txt