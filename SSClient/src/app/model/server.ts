import { Volume } from './volume';
import { ServerHistory } from './serverHistory';

export interface Server {
  id: number;
  name: string;
  networkDesc: string;
  fullCapacity: number;
  latestStorageReserved: number;
  latestStorageFree: number;
  latestStorageRatio: number;
  ram: number;
  ramUsage: number;
  cpuUsage: number;
  volumes: Set<Volume>;
  serverHistories: Set<ServerHistory>;
}
