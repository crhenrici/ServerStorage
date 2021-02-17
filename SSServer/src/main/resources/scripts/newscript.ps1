$volumes = Get-WmiObject win32_volume | Select-Object SystemName, Name, DriveLetter,
@{ Name = "CapacityGB"; Expression = { [math]::round($_.Capacity / 1GB, 2) } },
@{ Name = "FreeSpaceGB"; Expression = { [math]::round($_.FreeSpace / 1GB, 2) } },
@{ Name = "FreeSpacePercent"; Expression = { [math]::round(($_.FreeSpace / ($_.Capacity * 1.00)) * 100.00, 2) } },
@{ Name = "Date"; Expression = { $( Get-Date -f s ) } } |
        Sort-Object Name
$ramOutput = Get-WmiObject win32_physicalmemory |
        Select-Object @{n="Capacity";e={$_.Capacity/1GB}}
$cpuUsage = Get-Counter '\Processor(_total)\% Processor Time'|
        Select-Object -expand CounterSamples
$output = [ordered]@{
    cpuusage = $cpuUsage
    ram = $ramOutput
    volumes = $volumes
}
$jsonOut  = $output | ConvertTo-Json
Out-File -FilePath .\process.txt -InputObject $jsonOut -Encoding ascii