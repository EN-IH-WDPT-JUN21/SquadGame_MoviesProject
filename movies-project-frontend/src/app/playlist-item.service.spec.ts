import { TestBed } from '@angular/core/testing';

import { PlaylistItemService } from './playlist-item.service';

describe('PlaylistItemService', () => {
  let service: PlaylistItemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlaylistItemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
