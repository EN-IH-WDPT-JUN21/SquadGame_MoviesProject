/*export interface Movie {
    id: string,
    image: string,
    title: string,
    description: string
}*/

export class MovieResponse{
 
    constructor(private _results: MovieInfo[]){}

    public get results(): MovieInfo[] {
        return this._results;
    }
    public set results(value: MovieInfo[]) {
        this._results = value;
    }
}

interface MovieInfo{
    results: [{
        id: string,
        image: string,
        title: string,
        description:string}]
}
