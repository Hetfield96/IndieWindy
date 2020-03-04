using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Diagnostics;
using Microsoft.EntityFrameworkCore.Query;
using Microsoft.EntityFrameworkCore.Query.SqlExpressions;
using WebAPI.Migrations;

namespace DatabaseAPI.Services
{
    public class SongService : DatabaseBaseService<Song>
    {
        public SongService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb)
        { }
        
        public async Task<List<Song>> FindByName(string query)
        {
            var songs = await _indieWindyDb.Song
                .Include(s => s.Artist)
                .Include(s => s.Album)
                .ToListAsync();
            // TODO change on where and like - dapper
            return songs.Where(s => SearchService.StartsWith(s.Name, query)).ToList();
        }
        
        // TODO make dapper and do left join
        // public async Task<List<(Song s, int AppUserId)>> FindByNameWithAdded(string query, int userId)
        // {
        //     var songs = await _indieWindyDb.Song
        //         .Include(s => s.Artist)
        //         .Include(s => s.Album)
        //         .ToListAsync();
        //     
        //     // TODO change on where and like
        //     songs = songs.Where(s => SearchService.StartsWith(s.Name, query)).ToList();
        //
        //     var added = songs
        //         .Join(_indieWindyDb.UserSongLink,
        //             s => s.Id,
        //             l => l.SongId,
        //             (s, l) => (s, l.AppUserId))
        //         .Where(e => e.AppUserId == userId)
        //         .ToList();
        //
        //     return added;
        // }

    }
}