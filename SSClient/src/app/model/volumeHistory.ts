import { Volume } from './volume';

export interface VolumeHistory {
  volumeId: number;
  date: Date;
  storageUsed: number;
  storageFree: number;
  storageRatio: number;
  volume: Volume;
}
