import { Playlist } from './playlist.model';

export class PlaylistItemRequest{

    constructor(private id: string, private title: string, private description: string, private image: string, private playlist: Playlist){}
}
