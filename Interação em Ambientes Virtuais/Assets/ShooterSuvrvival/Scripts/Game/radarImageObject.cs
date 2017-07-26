﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class radarImageObject : MonoBehaviour {

    public Image image;

	// Use this for initialization
	void Start () {
        Radar.RegisterRadarObject(this.gameObject, image);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	//pre-build ondestroy
    private void OnDestroy()
    {
        Radar.RemoveRadarObject(this.gameObject);
    }
}
