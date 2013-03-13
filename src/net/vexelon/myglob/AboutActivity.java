/*
 * The MIT License
 *
 * Copyright (c) 2010 Petar Petrov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.vexelon.myglob;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);

		ImageView icLogo = (ImageView)findViewById(R.id.about_logo);
		icLogo.setImageResource(R.drawable.about_icon);

		PackageInfo pinfo = null;
		try {
			pinfo = this.getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_GIDS);
		}
		catch(Exception e) {
			//Log.e(TAG, e.getMessage());
		}

		StringBuilder sb = new StringBuilder(500);
		sb.append(getResString(R.string.app_name));
		sb.append("\n");
		sb.append(getResString(R.string.about_tagline));
		sb.append("\n");
		if ( pinfo != null ) {
			sb.append(getResString(R.string.about_version));
			sb.append(" ");
			sb.append(pinfo.versionName);
			sb.append("\n");
		}
		sb.append(getResString(R.string.about_author))
		.append("\n")
		.append("http://code.google.com/p/myglob")
		.append("\n")
		.append("\n")
		.append(getResString(R.string.about_icons_info))
		.append("\n")
		.append("http://www.famfamfam.com")
		.append("\n")
		.append("\n")
		.append(getResString(R.string.about_thanks))
		.append("\n").append("Atanas Atanasow (Testing)")
		.append("\n").append("Янчо Гинчев (Bug reports)")
		.append("\n").append("Svetlana Velkova (Bug reports)")
		.append("\n");

		this.setText(R.id.about_apptitle, sb.toString());
		Linkify.addLinks((TextView)findViewById(R.id.about_apptitle), Linkify.ALL);

	}

	void setText(int id, String text) {
		TextView tx = (TextView)findViewById(id);
		if ( tx != null )
			tx.setText(text);
	}

	String getResString(int id) {
		return this.getResources().getString(id);
	}
}
