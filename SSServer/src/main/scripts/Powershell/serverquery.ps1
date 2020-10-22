$output = Get-WmiObject win32_volume | Select-Object SystemName, Name, DriveLetter,
    @{ Name = "CapacityGB"; Expression = { [math]::round($_.Capacity / 1GB, 2) } },
    @{ Name = "FreeSpaceGB"; Expression = { [math]::round($_.FreeSpace / 1GB, 2) } },
    @{ Name = "FreeSpacePercent"; Expression = { [math]::round(($_.FreeSpace / ($_.Capacity * 1.00)) * 100.00, 2) } },
    @{ Name = "Date"; Expression = { $( Get-Date -f s ) } } |
        Sort-Object Name |
        Convertto-JSON

Invoke-RestMethod -Method Post -Uri "http://localhost:9010/service/save/volume" -Body ($output) -ContentType 'application/json'

$ramOutput = Get-WmiObject win32_physicalmemory |
        Select-Object @{n="Capacity";e={$_.Capacity/1GB}} |
        ConvertTo-Json

$cpuUsage = Get-Counter '\Processor(*)\% Processor Time'|
        Select-Object -expand CounterSamples |
        ConvertTo-Json

Invoke-RestMethod -Method Post -Uri "http://localhost:9010/service/save/server" -Body ($ramOutput) -ContentType 'application/json'
Invoke-RestMethod -Method Post -Uri "http://localhost:9010/service/save/server" -Body ($cpuUsage) -ContentType 'application/json
'