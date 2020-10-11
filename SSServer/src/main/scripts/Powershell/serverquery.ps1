﻿$output = Get-WmiObject win32_volume | Select-Object SystemName, Name, DriveLetter,
    @{ Name = "CapacityGB"; Expression = { [math]::round($_.Capacity / 1GB, 2) } },
    @{ Name = "FreeSpaceGB"; Expression = { [math]::round($_.FreeSpace / 1GB, 2) } },
    @{ Name = "FreeSpacePercent"; Expression = { [math]::round(($_.FreeSpace / ($_.Capacity * 1.00)) * 100.00, 2) } },
    @{ Name = "Date"; Expression = { $( Get-Date -f s ) } } |
        Sort-Object Name |
        Convertto-JSON

Invoke-RestMethod -Method Post -Uri "http://localhost:9010/service/save" -Body ($output) -ContentType 'application/json'