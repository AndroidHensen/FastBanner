# FastBanner

A simple, practical banner for Android.

<img src="/preview/preview1.png" height="400px"></img>

# Usage

You can directly copy the layout file style using the sample project.

### Step1

Add it in your root build.gradle at the end of repositories

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

### Step2

Add the dependency

```
dependencies {
	compile 'com.github.AndroidHensen:FastBanner:1.0'
}
```

### Step3

Add FastBanner in your layout

```
<com.handsome.library.banner.FastBanner
	android:id="@+id/fast_banner"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	app:fb_banner_ratio="3.2"                           // value = width/height
	app:fb_indication_gap="4dp"                         // default 4dp
	app:fb_indication_gravity="right"                   // default right
	app:fb_indication_off="@drawable/indication_off"    // default Color.WHITE
	app:fb_indication_on="@drawable/indication_on"      // default Color.RED
	app:fb_indication_size="8dp" />                     // default 8dp
```

### Step4

Add BannerAdapter for FastBanner

```
FastBanner fast_banner = (FastBanner) findViewById(R.id.fast_banner);
fast_banner.setAdapter(new BannerAdapter() {

	/**
	 * required
	 */
	@Override
	public View getView(int position) {
		ImageView imageView = new ImageView(MainActivity.this);
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		Glide.with(MainActivity.this).load(url_list.get(position)).into(imageView);
		return imageView;
	}
	
	/**
	 * required
	 */
	@Override
	public int getCount() {
		return url_list.size();
	}
	
	/**
	 * required
	 */
	@Override
	public void onClick(int position) {
		Toast.makeText(MainActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
	}

	/**
	 * Options:return banner title,default closed
	 */
	@Override
	public String getTitle(int position) {
		return title_list.get(position);
	}
	
	/**
	 * Options:default true
	 */
	@Override
	public boolean autoScroll() {
		return true;
	}
});
```

# Changelog

* 1.0
	* Initial release
	
# License

```
Copyright 2017 AndroidHensen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
