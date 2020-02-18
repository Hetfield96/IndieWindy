using JsonSerializer = System.Text.Json.JsonSerializer;


namespace DatabaseAPI.Models
{
    public static class JSONHelper
    {
        public static string ToJSON(this object obj)
        {
            var res = JsonSerializer.Serialize(obj);
            return res;
            // Creating BlogSites object  
            /*
            BlogSites bsObj = new BlogSites()  
            {  
                Name = "C-sharpcorner",  
                Description = "Share Knowledge"  
            };  
  
// Serializing object to json data  
            JavaScriptSerializer js = new JavaScriptSerializer();  
            string jsonData = js.Serialize(bsObj); // {"Name":"C-sharpcorner","Description":"Share Knowledge"}  
            return jsonData;*/
        }
    }
}