export interface Volume {
  id: number;
  serverId: number;
  name: string;
  desc: string;
  storageCapacity: number;
  storageReserved: number;
  storageFree: number;
  storagetRatio: number;
}
