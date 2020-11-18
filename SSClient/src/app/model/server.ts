import { Volume } from './volume';
import { ServerHistory } from './serverHistory';

export interface Server {
  name: string;
  fullCapacity: number;
  latestStorageReserved: number;
  latestStorageFree: number;
  latestStorageRatio: number;
  ram: number;
  cpuUsage: number;
  volumes: Set<Volume>;
  serverHistories: Set<ServerHistory>;
}
