#ifndef DRUMKNEE3D_H
#define DRUMKNEE3D_H

#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>
#include <android/native_window_jni.h>
#include <EGL/egl.h>
#include <GLES3/gl3.h>
#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

void nativeInit();
void nativeSetContext(AAssetManager* assetManager);
void nativeLoadAsset(const char* path);
void nativePlayNote(int note, int velocity);
void nativeStopNote(int note);
void nativeSetKit(int kitIndex);
void nativeResize(int w, int h);
void nativeDraw();
void nativeTouch(float x, float y, int action);
void nativeDestroy();

#ifdef __cplusplus
}
#endif

#endif // DRUMKNEE3D_H