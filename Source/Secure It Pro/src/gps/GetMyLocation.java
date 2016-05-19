package gps;

import bean.GPSLocation;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class GetMyLocation {
	AppLocationService appLocationService;

	String longitude;
	String latitude;
	Context c;

	public GetMyLocation(Context c) {
		// TODO Auto-generated constructor stub
		this.c = c;
	}

	public GPSLocation fetchLocation() {
		GPSLocation loc = new GPSLocation();
		appLocationService = new AppLocationService(c);
		Location nwLocation = appLocationService
				.getLocation(LocationManager.NETWORK_PROVIDER);

		if (nwLocation != null) {
			latitude = nwLocation.getLatitude() + "";
			longitude = nwLocation.getLongitude() + "";
			loc.setLatitude(latitude);
			loc.setLongitude(longitude);
			return loc;

		}
		return null;
	}

}
