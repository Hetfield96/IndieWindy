using System;

namespace DatabaseAPI.Utils
{
    public static class SearchUtil
    {
        public static bool StartsWith(string name, string query)
        {
            return name.IndexOf(query, StringComparison.OrdinalIgnoreCase) == 0;
        }
    }
}