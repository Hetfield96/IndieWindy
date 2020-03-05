using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using Dapper;
using DatabaseAPI;
using DatabaseAPI.Models;
using DatabaseAPI.Services;
using DatabaseAPI.Utils;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

namespace WebAPI
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            // Database services
            services.AddScoped<AppUserService>();
            services.AddScoped<ArtistService>();
            services.AddScoped<AlbumService>();
            services.AddScoped<SongService>();
            services.AddScoped<ConcertService>();
            services.AddScoped<UserArtistLinkService>();
            services.AddScoped<UserAlbumLinkService>();
            services.AddScoped<UserSongLinkService>();
            services.AddScoped<UserConcertLinkService>();
            services.AddScoped<SearchService>();
            
            services.AddControllers();
            
            services.AddEntityFrameworkNpgsql()
                .AddDbContext<IndieWindyDbContext>(opt =>
                opt.UseNpgsql(Configuration.GetSection("DB")?["ConnectionStrings"]));
            
            // Dapper mapping configuration
           DapperMappingConfiguration.Configure();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();
            
            app.UseEndpoints(endpoints => { endpoints.MapControllers(); });
        }
    }
}