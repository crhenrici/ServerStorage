import { Volume } from './volume';

export interface VolumeHistory {
  volumeId: number;
  storageUsed: number;
  storageFree: number;
  storageRatio: number;
  volume: Volume;
}
