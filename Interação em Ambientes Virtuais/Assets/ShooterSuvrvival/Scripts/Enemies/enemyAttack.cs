using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class enemyAttack : MonoBehaviour {
	private GameObject player;
	private Vector3 playerPos;
	private playerHealth ph;
	private bool inRange;
	private Animator animator;
	private float timeBetweenAttacks;
	private int hitDamage;
	private float timer;
	private float distanceToPlayer;
	// Use this for initialization
	void Start () {
		player = GameObject.FindGameObjectWithTag ("Player");
		ph = player.GetComponent<playerHealth> ();
		playerPos = player.transform.position;
		animator = GetComponent<Animator> ();
		timeBetweenAttacks = 0.5f;
		setEnemyDamage ();
		timer = 0f;
		distanceToPlayer = 3.5f;
	}
	
	// Update is called once per frame
	void Update () {
		timer += Time.deltaTime;

		checkOnRange ();
	
		animator.SetBool ("playerInRange",inRange);
		if(timer>= timeBetweenAttacks && inRange && ph.health > 0){
			attack ();
		}

		if(ph.health <= 0){
			animator.SetTrigger("playerDead");
		}
	}

	private void checkOnRange(){
		float distance;
		playerPos = player.transform.position;
		distance = Vector3.Distance (playerPos,transform.position);
		if (distance <= distanceToPlayer) {
			inRange = true;
		} else {
			inRange = false;
		}

	}

	private void attack(){
		timer = 0f;
		if(ph.health > 0){
			ph.takeDamage (hitDamage);
		}
	}

	private void setEnemyDamage(){

		switch(gameObject.name){
		case "zombieSlow(Clone)":
			hitDamage = 10;
			break;
		case "zombieAxe(Clone)":
			hitDamage = 15;
			break;
		case "zombieFast(Clone)":
			hitDamage = 10;
			break;
		default:
			hitDamage = 10;
			break;
		}
	}
}
