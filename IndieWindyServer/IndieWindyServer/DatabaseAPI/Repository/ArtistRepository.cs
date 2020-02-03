namespace DatabaseAPI.Repository
{
    /*
    public class ArtistRepository
    {
        private readonly string _connectionString = null;

        public ArtistRepository(IConfiguration configuration)
        {
            _connectionString = configuration.GetSection("DB")?["ConnectionStrings"];
        }

        public List<Artist> GetArtists()
        {
            using (var connection = new NpgsqlConnection(_connectionString))
            {
                connection.Open();
                var res = connection.Query<Artist>("select * from artist").ToList();
                return res;
            }
        }
    }*/
}