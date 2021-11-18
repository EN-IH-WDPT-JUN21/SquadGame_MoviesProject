export class PlaylistRequest{
    constructor(private playlistId: number, private userId: number, private title: string, private isFull: boolean ){}
}