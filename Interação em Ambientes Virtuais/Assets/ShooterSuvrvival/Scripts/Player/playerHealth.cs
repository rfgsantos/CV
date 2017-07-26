using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class playerHealth : MonoBehaviour {
	public int health;
	private AudioSource[] audio;
	// Use this for initialization
	void Start () {
		audio = GetComponents<AudioSource> ();
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	public void takeDamage(int damage){
		health -= damage;
		//animação de damage
		audio[2].Play();
		//mais o som
		if(health <= 0){
			SceneManager.LoadScene (2);
		}
	}
}
