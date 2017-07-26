using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class pickupBoost : MonoBehaviour {

	private GameObject player;
	private gameManager gm;

	private void OnTriggerEnter(Collider other)
	{

		//do something to player here.
		//assim zombies não apanham os boosts
		if(other.name == "Player"){
			Destroy(this.gameObject);
			//activar boost damage
			gm.activateBoost ();
		}

	}

	// Use this for initialization
	void Start () {
		player = GameObject.FindGameObjectWithTag ("Player");
		gm = GameObject.Find ("gameManager").GetComponent<gameManager> ();
	}
}
