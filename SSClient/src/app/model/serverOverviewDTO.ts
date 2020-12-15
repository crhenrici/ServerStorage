export interface ServerOverviewDTO {
    numberOfServers: number;
    totalCapacity: number;
    totalCapacityUsed: number;
    totalCapacityFree: number;
    totalUsedCapacityRatio: number;
}