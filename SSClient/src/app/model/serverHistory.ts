import { Server } from './server';

export interface ServerHistory {
    freeStorage: number;
    storageReserved: number;
    storageRatio: number;
    server: Server;
}
