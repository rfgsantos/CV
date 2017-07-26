using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PickUp : MonoBehaviour {
	private GameObject player;
	private playerHealth ph;

    private void OnTriggerEnter(Collider other)
    {
		
        //do something to player here.
		//assim zombies não apanham os boosts
		if(other.name == "Player"){
			ph.health += 40;
			Destroy(this.gameObject);
		}
        
    }
		
    // Use this for initialization
    void Start () {
		player = GameObject.FindGameObjectWithTag ("Player");
		ph = player.GetComponent<playerHealth> ();
	}

}
