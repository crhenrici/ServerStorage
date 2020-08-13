export interface Server {
  id: number;
  name: string;
  fullCapacity: number;
  storageReserved: number;
  storageFree: number;
  storageRatio: number;
  ram: number;
  cpuUsage: number;
}
