export class Playlist {

    constructor(private _playlistId: number, private _userId: number, private _title: string, private _isFull: boolean ){}

   public get playlistId(): number {
        return this._playlistId;
    }
    public set playlistId(value: number) {
        this._playlistId = value;
    }
    public get isFull(): boolean {
        return this._isFull;
    }
    public set isFull(value: boolean) {
        this._isFull = value;
    }
    public get title(): string {
        return this._title;
    }
    public set title(value: string) {
        this._title = value;
    }
    public get userId(): number {
        return this._userId;
    }
    public set userId(value: number) {
        this._userId = value;
    }
}