import { Server } from './server';
import { VolumeHistory } from './volumeHistory';


export interface Volume {
  name: string;
  desc: string;
  date: Date;
  fullCapacity: number;
  latestStorageReserved: number;
  latestStorageFree: number;
  latestStoragetRatio: number;
  server: Server;
  volumeHistories: Set<VolumeHistory>;
}
