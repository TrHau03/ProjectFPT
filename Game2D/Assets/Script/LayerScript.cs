using System;
using System.Collections.ObjectModel;
using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;
using UnityEngine.UI;

public class LayerScript : MonoBehaviour
{
    //aminator
    public Animator animator;
    private float speed;
    bool isRight;
    public bool isRunning, isJump;
    public new Rigidbody2D rigidbody2D;
    //music
    private AudioSource audioSource;
    public AudioClip coinclip;
    public AudioClip jumpclip;
    //Score
    private int coin;
    public Text coinText;
    //Time
    private int time;
    public Text timeText;
    private bool isALive;
    public GameObject fire;
    //Position
    // Start is called before the first frame update
    //menu
    private bool isActive;
    public GameObject menu;
    public GameObject menuGameover;
    public GameObject menuAndGame;
    public Text menuText;
    private int life;
    public Text lifeText;
    private Vector2 originalPosition;
    public Text EndTextscore;
    public Text EndTexttime;

    //Bridge
    public GameObject bridge;
    public GameObject lastBridge;
    public float bridgeSpeed;
    public float leftBridge;
    private bool check;
    void Start()
    {
        animator = GetComponent<Animator>();
        speed = 6f;
        rigidbody2D = GetComponent<Rigidbody2D>();
        isRight = true;
        isRunning = false;
        isJump = false;
        audioSource = GetComponent<AudioSource>();
        coin = 0;
        isALive = true;
        time = 0;
        timeText.text = time + "s";
        StartCoroutine(updateTime());
        originalPosition = transform.localPosition;
        isActive = false;
        life = 3;
        check = false;
    }
    //setText thời gian
    IEnumerator updateTime(){
        while (isALive)
        {
            time++;
            timeText.text = time + "s";
            yield return new WaitForSeconds(1);
        }
    }

    // Update is called once per frame
    void Update()
    {
        
        animator.SetBool("isRunning", isRunning);
        animator.SetBool("isJump", isJump);
        if(Input.GetKeyDown(KeyCode.Space)){
                GameObject fireball = Instantiate(fire);
                fireball.transform.position = new Vector3(
                    transform.position.x + (isRight ? 0.6f : -0.6f),
                    transform.position.y,
                    transform.position.z
                );
                fireball.GetComponent<FireScript>().setSpeed(isRight ? 7 : -7);
        }
        if(Input.GetKeyDown(KeyCode.M)){
            if(!isActive){
            isActive = true;
            menu.SetActive(true);
            Time.timeScale = 0;
            menuText.text = "Your score " + coin;
          }else{
            isActive = false;
            menu.SetActive(false);
            Time.timeScale = 1;
          }
        }
        if (Input.GetKey(KeyCode.RightArrow))
            {
                isRunning = true;
                animator.Play("Running");
                if(isRight == false){
                    Vector2 scale = transform.localScale;
                    scale.x *= scale.x  < 0 ? -1 : 1;
                    transform.localScale = scale;
                    isRight = true;
                }
                //rigidbody2D.velocity = new Vector2(speed,0);
                transform.Translate(Vector3.right * speed * Time.deltaTime);
        }else if (Input.GetKey(KeyCode.LeftArrow))
        {
            isRunning = true;
            animator.Play("Running");
            if(isRight == true){
                Vector2 scale = transform.localScale;
                scale.x *= scale.x  < 0 ? 1 : -1;
                transform.localScale = scale;
                isRight = false;
            }
            //rigidbody2D.velocity = new Vector2(-speed,0);
            transform.Translate(Vector3.left * speed * Time.deltaTime);
        }else if (Input.GetKeyDown(KeyCode.UpArrow))
        {
            if(isJump == false){
                PlaySound(jumpclip);
                rigidbody2D.AddForce(new Vector2(0, 500));
            isJump = true;
            }
        }else{
             isRunning = false;
        }
       var bridgeX = bridge.transform.position.x;
       if(check){
            if(bridgeX > leftBridge){
                Vector3 vector3;
                vector3 = new Vector3(-1, 0, 0);
                bridge.transform.Translate(vector3 * bridgeSpeed * Time.deltaTime);
            }
       }
       
         
    }
        //2 box va chạm     
    private void OnCollisionEnter2D(Collision2D other) {
        
            if(other.gameObject.CompareTag("sangach")){
                isJump = false;
            }else if(other.gameObject.CompareTag("turtle")){
                Vector2 direction = other.GetContact(0).normal;
                if(Mathf.Round(direction.x) == -1 || Mathf.Round(direction.x) == 1){
                    checkLives();
                    transform.localPosition = originalPosition;
                }
            }else if(other.gameObject.CompareTag("mushroom")){
                Vector2 direction = other.GetContact(0).normal;
                if(Mathf.Round(direction.x) == -1 || Mathf.Round(direction.x) == 1){
                    checkLives();
                    transform.localPosition = originalPosition;
                }
            }else if(other.gameObject.CompareTag("prihana")){
                Vector2 direction = other.GetContact(0).normal;
                if(Mathf.Round(direction.x) == -1 || Mathf.Round(direction.x) == 1 || Mathf.Round(direction.y) == 1){
                    checkLives();
                    transform.localPosition = originalPosition;
                }
            }else if(other.gameObject.CompareTag("flagend")){
                SceneManager.LoadScene(2);
            }else if(other.gameObject.CompareTag("key")){         
                check = true;
                Destroy(lastBridge.gameObject, 0);
                Destroy(other.gameObject, 0);
            }else if(other.gameObject.CompareTag("girl")){
                    menuAndGame.SetActive(true);
                    Time.timeScale = 0;
            }

            
            
        }
    private void OnTriggerEnter2D(Collider2D other) {
            if(other.gameObject.CompareTag("coin")){
                //phát nhạc, tăng điểm, biến mất
                PlaySound(coinclip);

                Destroy(other.gameObject, 0);

                coin++;
                coinText.text = coin + " x";
            }
            if(other.gameObject.CompareTag("fireboss") || other.gameObject.CompareTag("fireboss2")){
                    checkLives();
                    transform.localPosition = originalPosition;
            }
            
    }
    private void PlaySound(AudioClip clip){
        audioSource.PlayOneShot(clip);
    }
    public void QuitGame(){
        Application.Quit();//outgame
    }
    public void ResumeGame(){
        isActive = false;
            menu.SetActive(false);
            Time.timeScale = 1;
    }
    public void NextScene(){
            SceneManager.LoadScene(2);
    }
    private void checkLives(){
        if(life > 1){
            life--;
            lifeText.text = life +"";
        }else{
            menuGameover.SetActive(true);
            Time.timeScale = 0;
            EndTexttime.text = "Your time " + time; 
            EndTextscore.text = "Your score " + coin; 
        }
    }
    public void Newgames(){
        Time.timeScale = 1;
        SceneManager.LoadScene(1);
        menuGameover.SetActive(false);
    }
}
