import { Pipe, PipeTransform } from "@angular/core";

@Pipe({name: 'notRound'})
export class NotRoundPipe implements PipeTransform {
    transform(value: number): number {
        var num = Math.floor(value * 100)/100;
        return num;
    }
}