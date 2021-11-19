import { Playlist } from './playlist.model';

export class PlaylistItemRequest{

    constructor(private itemId: number, private movieId: string, private title: string, private description: string, private imageUrl: string, private playlist: Playlist){}
}
