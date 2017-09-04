/*
 * Copyright (C) 2013-2014 Laurent CLOUET
 * Author Laurent CLOUET <laurent.clouet@nopnop.net>
 *
 * This program is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; version 2
 * of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.sheepit.client.hardware.gpu;

import java.util.LinkedList;
import java.util.List;

import com.sheepit.client.hardware.gpu.nvidia.Nvidia;
import com.sheepit.client.hardware.gpu.opencl.OpenCL;

public class GPU {
	public static List<GPUDevice> devices = null;
	
	public static boolean generate() {
		devices = new LinkedList<GPUDevice>();
		
		List<GPUDevice> gpus = Nvidia.getGpus();
		if (gpus != null) {
			devices.addAll(gpus);
		}
		
		gpus = OpenCL.getGpus();
		if (gpus != null) {
			devices.addAll(gpus);
		}
		
		return true;
	}
	
	public static List<String> listModels() {
		if (devices == null) {
			generate();
		}
		
		List<String> devs = new LinkedList<String>();
		for (GPUDevice dev : devices) {
			devs.add(dev.getModel());
		}
		return devs;
	}
	
	public static List<GPUDevice> listDevices() {
		if (devices == null) {
			generate();
		}
		
		return devices;
	}
	
	public static GPUDevice getGPUDevice(String device_model) {
		if (device_model == null) {
			return null;
		}
		
		if (devices == null) {
			generate();
		}
		
		if (devices == null) {
			return null;
		}
		
		for (GPUDevice dev : devices) {
			if (device_model.equals(dev.getId()) || device_model.equals(dev.getModel())) {
				return dev;
			}
		}
		return null;
	}
}
