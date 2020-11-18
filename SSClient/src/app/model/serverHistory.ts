import { Server } from './server';

export interface ServerHistory {
    id: number;
    ram: number;
    cpuUsage: number;
    server: Server;
}
