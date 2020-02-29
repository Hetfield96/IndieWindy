using System;
using System.Security.Cryptography;
using System.Text;

namespace DatabaseAPI.Services
{
    public static class PasswordHasherService
    {
        public static MD5 _md5Hasher = MD5.Create();
        
        public static string GetMd5Hash(string input)
        {
            // Convert the input string to a byte array and compute the hash.
            var dataBytes = _md5Hasher.ComputeHash(Encoding.UTF8.GetBytes(input));

            // Create a new Stringbuilder to collect the bytes
            // and create a string.
            var sBuilder = new StringBuilder();

            // Loop through each byte of the hashed data 
            // and format each one as a hexadecimal string.
            for (int i = 0; i < dataBytes.Length; i++)
            {
                sBuilder.Append(dataBytes[i].ToString("x2"));
            }

            // Return the hexadecimal string.
            return sBuilder.ToString();
        }

        // Verify a hash against a string.
        public static bool VerifyMd5Hash(string input, string hash)
        {
            // Hash the input.
            var hashOfInput = GetMd5Hash(input);

            // Create a StringComparer an compare the hashes.
            StringComparer comparer = StringComparer.OrdinalIgnoreCase;

            if (0 == comparer.Compare(hashOfInput, hash))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}