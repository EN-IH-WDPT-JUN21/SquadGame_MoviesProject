import { Playlist } from './playlist.model';

export class PlaylistItem{

    constructor(private _itemId: number, private _id: string, private _title: string, private _description: string, private _image: string,  private _playlist: Playlist){}

    public get playlist(): Playlist {
        return this._playlist;
    }
    public set playlist(value: Playlist) {
        this._playlist = value;
    }
 
    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }
    public get title(): string {
        return this._title;
    }
    public set title(value: string) {
        this._title = value;
    }
    public get movieId(): string {
        return this._id;
    }
    public set movieId(value: string) {
        this._id = value;
    }
    public get itemId(): number {
        return this._itemId;
    }
    public set itemId(value: number) {
        this._itemId = value;
    }

    public get image(): string {
        return this._image;
    }
    public set image(value: string) {
        this._image = value;
    }

}
