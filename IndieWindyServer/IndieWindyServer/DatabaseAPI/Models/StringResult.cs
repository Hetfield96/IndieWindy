using System;
using System.Runtime.Serialization;

namespace DatabaseAPI.Models
{
    [DataContract]
    public class StringResult
    {
        [DataMember]
        public string Result { get; set; }
    }
}