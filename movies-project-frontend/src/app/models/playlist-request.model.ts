export class PlaylistRequest{
    constructor(private userId: number, private title: string, private isFull: boolean ){}
}

export class PlaylistRequestId{
    constructor(private playlistId:number, private userId: number, private title: string, private isFull: boolean ){}
}
