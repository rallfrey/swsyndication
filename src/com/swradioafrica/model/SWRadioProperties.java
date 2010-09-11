package com.swradioafrica.model;

import siena.Column;
import siena.Id;
import siena.Model;
import siena.Query;
import siena.Table;

@Table("swradio_properties")
public class SWRadioProperties extends Model {

	@Id public Long id;
	
	@Column("twitterUsername") public String twitterUsername;
	@Column("twitterPassword") public String twitterPassword;
	@Column("JMPUsername") public String JMPUsername;
	@Column("JMPKey") public String JMPKey;
	@Column("consumerKey") public String consumerKey;
	@Column("consumerSecret") public String consumerSecret;
	@Column("twitterAccessToken") public String twitterAccessToken;
	@Column("twitterAccessTokenSecret") public String twitterAccessTokenSecret;

	public static Query<SWRadioProperties> all() {
		return Model.all(SWRadioProperties.class);
	}	
	
}
