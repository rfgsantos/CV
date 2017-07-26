using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class gameManager : MonoBehaviour {

	public static float health;
	private GameObject player;
    private Animator animator;
    public Slider healthBar;
	private playerHealth pHealth;
	public Text scoreText;
	private int score;
	public GameObject[] enemies;
	private playerShooting playerShoot;
	private bool damageBoost;
	private float boostTime;
	private float boostTimeDuration;
   
    // Use this for initialization
    void Start()
    {
       	//InvokeRepeating("ReduceHealth",1,1);
		player = GameObject.FindGameObjectWithTag("Player");
		//debug
		playerShoot = player.GetComponent<playerShooting> ();
		//debug
		pHealth = player.GetComponent<playerHealth> ();
        animator = player.GetComponent<Animator>();
		//health = pHealth.health;
		score = 0;
		//dura 15 segundos o booster
		boostTimeDuration = 15.0f;
		//começa a falso, não apanhou damage booster
		damageBoost = false;
    }
	
	// Update is called once per frame
	void Update () {
		scoreText.text = "Score: " + score;
		healthBar.value = pHealth.health;
		//apanhou damage booster?
		if(damageBoost){
			playerShoot.damage = 30f;
			if(Time.time - boostTime >= boostTimeDuration){
				Debug.Log ("acabou o booster");
				playerShoot.damage = 10f;
				damageBoost = false;
			}
		}
	}

	public void updateScore(int value){
		this.score += value;
	}

	public void activateBoost(){
		damageBoost = true;
		boostTime = Time.time;
	}
		
}
