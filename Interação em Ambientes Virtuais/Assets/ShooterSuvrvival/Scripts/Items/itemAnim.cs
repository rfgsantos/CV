using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class itemAnim : MonoBehaviour {
	private float speed;
	private bool upDown;
	private Vector3 startPosition, endPosition1,endPosition2;
	// Use this for initialization
	void Start () {
		speed = 0.5f;
		startPosition = transform.position;
		endPosition1 = startPosition + new Vector3 (0,0.5f,0);
		endPosition2 = startPosition + new Vector3 (0,-0.5f,0);
		upDown = true;
	}
	
	// Update is called once per frame
	void FixedUpdate () {

		if (upDown) {
			transform.Translate (0, speed * Time.deltaTime, 0);
		} else {
			transform.Translate (0, -speed * Time.deltaTime, 0);
		}

		if (transform.position == endPosition1) {
			upDown = false;
		} 
		if(transform.position == endPosition2){
			upDown = true;
		}

	}
}
