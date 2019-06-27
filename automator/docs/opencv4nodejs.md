# Installation

## Start with [node-gyp](https://github.com/nodejs/node-gyp)

You can install with `npm`:

``` bash
$ npm install -g node-gyp
```

You will also need to install:

### On Windows

#### Option 1

From an elevated PowerShell or CMD.exe (run as Administrator), install all the required tools and configurations using Microsoft's [windows-build-tools](https://github.com/felixrieseberg/windows-build-tools) using `npm install --global --production windows-build-tools`.

If you already have Visual Studio installed, then `Visual Studio Build Tools` may fail. In that case launch cmd and enter `npm config set msvs_version 2017`.

#### Option 2

Install tools and configuration manually:
   * Install Visual C++ Build Environment: [Visual Studio Build Tools](https://visualstudio.microsoft.com/thank-you-downloading-visual-studio/?sku=BuildTools)
   (using "Visual C++ build tools" workload) or [Visual Studio 2017 Community](https://visualstudio.microsoft.com/pl/thank-you-downloading-visual-studio/?sku=Community)
   (using the "Desktop development with C++" workload)
   * Install [Python 2.7](https://www.python.org/downloads/) (`v3.x.x` is not supported), and run `npm config set python python2.7` (or see below for further instructions on specifying the proper Python version and path.)
   * Launch cmd, `npm config set msvs_version 2017`

   If the above steps didn't work for you, please visit [Microsoft's Node.js Guidelines for Windows](https://github.com/Microsoft/nodejs-guidelines/blob/master/windows-environment.md#compiling-native-addon-modules) for additional tips.

If you have multiple Python versions installed, you can identify which Python
version `node-gyp` uses by setting the '--python' variable:

``` bash
$ node-gyp --python /path/to/python2.7
```

If `node-gyp` is called by way of `npm`, *and* you have multiple versions of
Python installed, then you can set `npm`'s 'python' config key to the appropriate
value:

``` bash
$ npm config set python /path/to/executable/python2.7
```

If `windows-build-tools` was installed, the Python 2.7 path should be `C:\Users\<user>\.windows-build-tools\python27\`.

Use the command below to check `npm`'s 'python' config key value:

``` bash
$ npm config get python
```

***

## Install CMake
Download and install `x64` version from: [CMake Downloads](https://cmake.org/download)

Add to `PATH` during install

Will need to execute remaining commands in new terminal for `cmake` to be recognized. Use `cmake --version` to confirm that it's installed and available.

***

## Install [opencv4nodejs](https://github.com/justadudewhohacks/opendv4nodejs):

### Auto build

Verify that git is installed by using `git --version`; install if necessary.

If you do not want to set up OpenCV on your own you can simply let this package auto install OpenCV 3.4 + <a href="https://github.com/opencv/opencv_contrib"><b>OpenCV contrib 3.4</b></a> (might take some time):
``` bash
$ npm i -g opencv4nodejs
```

I've gotten errors about Visual Studio on a clean install (using just the windows-build-tools; not Visual Studio), and resolved that with a restart. I had also installed [Visual Studio Build Tools](https://visualstudio.microsoft.com/thank-you-downloading-visual-studio/?sku=BuildTools), but the restart might have been all that was needed.

In order to prevent build errors during an ```npm install```, your ```package.json``` should not include ```opencv4nodejs```, and instead should include/require the global package either by requiring it by absolute path or setting the ```NODE_PATH``` environment variable to ```/usr/lib/node_modules``` in your Dockerfile and requiring the package as you normally would.

I chose to add a `NODE_PATH` environment variable. The install path will be displayed in the output of the previous command, but for me it was in `C:\Users\<user>\AppData\Roaming\npm\node_modules`.